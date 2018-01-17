package es.jarroyo.daggerandkotlin.ui.home.presenter

import es.jarroyo.daggerandkotlin.app.navigator.Navigator
import es.jarroyo.daggerandkotlin.domain.model.DataHome
import es.jarroyo.daggerandkotlin.domain.usecase.home.GetHomeRequest
import es.jarroyo.daggerandkotlin.domain.usecase.home.GetHomeResponse
import es.jarroyo.daggerandkotlin.domain.usecase.home.GetHomeUseCase
import es.jarroyo.daggerandkotlin.ui.base.Presenter
import es.jarroyo.daggerandkotlin.ui.home.HomeDisplayModel
import es.jarroyo.daggerandkotlin.ui.home.activity.HomeView

/**
 * Created by javierarroyo on 29/12/17.
 */
class HomePresenter (override val view: HomeView,
                     override val navigator: Navigator,
                     var getHomeUseCase: GetHomeUseCase) :
        Presenter<HomeView> , GetHomeResponse {

    companion object {
        private val CHARACTER_CAPTAIN_AMERICA_ID = 1009220
        private val MAX_COMICS = 20
    }

    private var comics = listOf<DataHome>()


    override fun onDataHomeReceived(comics: List<DataHome>) {
        this.comics = comics
        val comicsDisplayModel = HomeDisplayModel(comics)
        view.setDataInHomeList(comicsDisplayModel)
        view.hideProgressWheel()
    }

    fun onItemOnListClicked(position: Int) {
        val comicSelected = comics[position]
        if (comicSelected.hasAllInfo()) {
            //navigator.toHomeDetail(comicSelected)
            view.showHomeHasNotAllInfoError()
        } else {
            view.showHomeHasNotAllInfoError()
        }
    }

    override fun clearView() {
        view.hideProgressWheel()
    }

    fun getHome() {
        val request = GetHomeRequest(CHARACTER_CAPTAIN_AMERICA_ID, MAX_COMICS)
        getHomeUseCase.execute(request, this)
    }
}