package com.example.thuc_hanh_2

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class OnboardingViewModel : ViewModel() {
    private val _currentPage = mutableStateOf(0)
    val currentPage: State<Int> = _currentPage

    val items = listOf(
        OnboardingItem(
            "Easy Time Management",
            "With management based on priority and daily tasks, daily tasks, it will give you convenience in managing and determining and determining the tasks that must be done first",
            R.drawable.image1
        ),
        OnboardingItem(
            "Increase Work Effectiveness",
            "Time management and the determination of more important tasks will give your job statistics better and always improve",
            R.drawable.image2
        ),
        OnboardingItem(
            "Reminder Notification",
            "This advantage of this application is that it also provides reminders for you so you don't forget to keep doing your assignments well and according to the time you have set",
            R.drawable.image3
        )
    )

    fun nextPage() {
        if (_currentPage.value < items.size - 1) {
            _currentPage.value += 1
        }
    }

    fun previousPage() {
        if (_currentPage.value > 0) {
            _currentPage.value -= 1
        }
    }
}