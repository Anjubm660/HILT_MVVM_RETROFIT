package com.anju.hilt_mvvm_retro
import androidx.lifecycle.Observer
import com.anju.hilt_mvvm_retro.character.CharacterFragment
import com.anju.hilt_mvvm_retro.hiltModule.NetworkModule
import com.anju.hilt_mvvm_retro.model.UsersLibrary
import com.anju.hilt_mvvm_retro.repository.UsersRepo
import com.anju.hilt_mvvm_retro.staff.StaffFragment
import com.anju.hilt_mvvm_retro.student.StudentFragment
import com.anju.hilt_mvvm_retro.viewmodel.UsersDetailsViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
@UninstallModules(NetworkModule::class)
class UsersAPITest{

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var apiConsumer: UsersRepo

    private lateinit var usersDetailsViewModel: UsersDetailsViewModel


    private lateinit var characterFragment: CharacterFragment
    private lateinit var staffFragment: StaffFragment
    private lateinit var studentFragment: StudentFragment

    @Before
    fun init() {
        hiltRule.inject()
        usersDetailsViewModel = UsersDetailsViewModel(apiConsumer)
    }

    @Test
    fun testFetchActorsDataSuccess() {
      Observer<UsersLibrary?> { characters ->
            assertNotNull(characters)
            assertEquals(characterFragment.totalItemCount, characters?.size)
        }
        usersDetailsViewModel.fetchInitialCharacters()
    }

    @Test
    fun testFetchCharactersDataFailure() {
        Observer<UsersLibrary?> { characters ->
            assertNotNull(characters)
            assertEquals(characterFragment.totalItemCount - 1, characters?.size)
        }
        usersDetailsViewModel.fetchInitialCharacters()
    }

    @Test
    fun testFetchStaffsDataSuccess() {
      Observer<UsersLibrary?> { staff ->
            assertNotNull(staff)

            assertEquals(staffFragment.totalItemCount,staff?.size)
        }
        usersDetailsViewModel.fetchInitialStaff()
    }



    @Test
    fun testFetchStaffsDataFailure() {
       Observer<UsersLibrary?> { staff ->
            assertNotNull(staff)
            // assertEquals(fragmentStaffList.allStaffs - 1, staff?.size)
            assertEquals(staffFragment.totalItemCount-1,staff?.size)
        }
        usersDetailsViewModel.fetchInitialStaff()
    }

    @Test
    fun testFetchStudentsDataSuccess() {
    Observer<UsersLibrary?> { students ->
            assertNotNull(students)
            assertEquals(studentFragment.totalItemCount, students?.size)
        }
        usersDetailsViewModel.fetchInitialStudents()
    }

    @Test
    fun testFetchStudentsDataFailure() {
       Observer<UsersLibrary?> { students ->
            assertNotNull(students)
            assertEquals(studentFragment.totalItemCount - 1, students?.size)
        }
        usersDetailsViewModel.fetchInitialStudents()
    }
}