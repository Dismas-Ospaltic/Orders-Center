package com.ord.orderscenter.di


import com.ord.orderscenter.data.localdatabase.OrderDatabase
import com.ord.orderscenter.repository.GeneralOrdersRepository
import com.ord.orderscenter.repository.IndividualItemRepository
import com.ord.orderscenter.viewmodel.GeneralOrderViewModel
import com.ord.orderscenter.viewmodel.IndividualOrderViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val appModule = module {
//  Define ViewModel injection

    single { OrderDatabase.getDatabase(get()).generalDao() }
     single { GeneralOrdersRepository(get()) }
      viewModel{GeneralOrderViewModel(get())}

    single { OrderDatabase.getDatabase(get()).individualDao() }
    single { IndividualItemRepository(get()) }
    viewModel{ IndividualOrderViewModel(get())}


}