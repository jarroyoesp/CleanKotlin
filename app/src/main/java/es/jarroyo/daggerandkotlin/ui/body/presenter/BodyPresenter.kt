package es.jarroyo.daggerandkotlin.ui.body.presenter

import es.jarroyo.daggerandkotlin.app.navigator.Navigator
import es.jarroyo.daggerandkotlin.domain.model.BodyPart
import es.jarroyo.daggerandkotlin.domain.usecase.body.get.GetPainRequest
import es.jarroyo.daggerandkotlin.domain.usecase.body.get.GetPainResponse
import es.jarroyo.daggerandkotlin.domain.usecase.body.get.GetPainUseCase
import es.jarroyo.daggerandkotlin.domain.usecase.body.save.SavePainRequest
import es.jarroyo.daggerandkotlin.domain.usecase.body.save.SavePainResponse
import es.jarroyo.daggerandkotlin.domain.usecase.body.save.SavePainUseCase
import es.jarroyo.daggerandkotlin.ui.base.Presenter
import es.jarroyo.daggerandkotlin.ui.body.activity.BodyView

/**
 * Created by javierarroyo on 29/12/17.
 */
class BodyPresenter(override val view: BodyView,
                    override val navigator: Navigator,
                    var savePainUseCase: SavePainUseCase,
                    var getPainUseCase: GetPainUseCase
) :
        Presenter<BodyView> , SavePainResponse, GetPainResponse {

    fun getPainBodyParts() {
        val request = GetPainRequest()
        getPainUseCase.execute(request, this)
    }

    fun savePainBodyPart(bodyPart: BodyPart) {
        val request = SavePainRequest(bodyPart)
        savePainUseCase.execute(request, this)

    }

    override fun onSavePainReceived(bodyPartList: List<BodyPart>) {
        view.onSavePain()
    }

    override fun clearView() {
        view.hideBodyLoading()
    }

    override fun onPainListReceived(bodyPartList: List<BodyPart>) {
        view.onPainBodyPartsReceived(bodyPartList)
    }
}
