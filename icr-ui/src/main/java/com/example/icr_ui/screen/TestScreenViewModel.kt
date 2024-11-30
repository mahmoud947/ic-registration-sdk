package com.example.icr_ui.screen

import android.util.Log
import com.example.icr_core.base.ICRViewModel
import com.example.icr_core.base.Resource
import com.example.icr_domain.models.ICRUser
import com.example.icr_domain.usecases.RegisterNewUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn

private const val TAG = "TestScreenViewModel"
class TestScreenViewModel(
    private val insertNewUserUseCase: RegisterNewUserUseCase
) : ICRViewModel<TestContract.Event, TestContract.State>() {
    override fun setInitialState(): TestContract.State = TestContract.State()

    override fun handleEvents(event: TestContract.Event) {
        when (event) {
            TestContract.Event.InsertUser -> insertNewUser()
        }
    }

    private fun insertNewUser() = launchCoroutine(Dispatchers.IO) {
        insertNewUserUseCase(
            input = ICRUser(
                phoneNumber = "012312321321",
                email = "email.com",
                password = "123123",
                username = "mahmoud"
            )
        ).flowOn(Dispatchers.IO)
            .collectLatest { resource ->
                when (resource) {
                    is Resource.Error -> {
                        Log.d(TAG, "insertNewUser: ${resource.exception.message}")
                    }
                    Resource.Loading -> {
                        Log.d(TAG, "insertNewUser: Loading")
                    }
                    is Resource.Success -> {
                        Log.d(TAG, "insertNewUser: ${resource.data}")
                    }
                }
            }
    }
}