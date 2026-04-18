package com.example.expensetracker.di

import com.example.expensetracker.domain.repository.LocalRepository
import com.example.expensetracker.domain.usecases.AddExpenseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideAddExpenseUseCase(repo: LocalRepository) =
        AddExpenseUseCase(repo)
}