package es.jarroyo.daggerandkotlin.app.di.module

import dagger.Module
import dagger.Provides
import es.jarroyo.daggerandkotlin.app.navigator.Navigator
import es.jarroyo.daggerandkotlin.ui.App
import javax.inject.Singleton


@Module
class ApplicationModule(val app: App){
    @Provides @Singleton
    fun provideApp(): App = app

    @Provides @Singleton
    fun provideNavigator(): Navigator = Navigator()
}