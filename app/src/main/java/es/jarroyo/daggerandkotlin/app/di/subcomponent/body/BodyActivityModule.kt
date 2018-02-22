package es.jarroyo.daggerandkotlin.app.di.subcomponent.body

import dagger.Module
import dagger.Provides
import es.jarroyo.daggerandkotlin.app.di.module.ActivityModule
import es.jarroyo.daggerandkotlin.app.navigator.Navigator
import es.jarroyo.daggerandkotlin.domain.usecase.body.SavePainUseCase
import es.jarroyo.daggerandkotlin.ui.body.activity.BodyActivity
import es.jarroyo.daggerandkotlin.ui.body.activity.BodyView
import es.jarroyo.daggerandkotlin.ui.body.presenter.BodyPresenter

@Module
class BodyActivityModule (activity: BodyActivity) : ActivityModule(activity) {

    @Provides
    fun provideBodyView(): BodyView = activity as BodyView


    @Provides
    fun provideBodyPresenter(view: BodyView, navigator: Navigator, savePainUseCase: SavePainUseCase)
            = BodyPresenter(view, navigator, savePainUseCase)
}