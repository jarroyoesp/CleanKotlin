package es.jarroyo.daggerandkotlin.ui.body.presenter

import es.jarroyo.daggerandkotlin.app.navigator.Navigator
import es.jarroyo.daggerandkotlin.domain.model.DataHome
import es.jarroyo.daggerandkotlin.domain.usecase.body.GetBodyResponse
import es.jarroyo.daggerandkotlin.ui.base.Presenter
import es.jarroyo.daggerandkotlin.ui.body.activity.BodyView

/**
 * Created by javierarroyo on 29/12/17.
 */
class BodyPresenter(override val view: BodyView,
                    override val navigator: Navigator//, var getBodyUseCase: GetBodyUseCase
) :
        Presenter<BodyView> , GetBodyResponse {

    override fun onBodyPartsReceived(comics: List<DataHome>) {
        view.setBodyParts()
    }

    override fun clearView() {
        view.hideBodyLoading()
    }
}