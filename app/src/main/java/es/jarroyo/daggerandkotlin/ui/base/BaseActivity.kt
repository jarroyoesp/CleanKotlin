package es.jarroyo.daggerandkotlin.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import es.jarroyo.daggerandkotlin.app.di.component.ApplicationComponent
import es.jarroyo.daggerandkotlin.app.navigator.Navigator
import es.jarroyo.daggerandkotlin.ui.App
import javax.inject.Inject


abstract class BaseActivity: AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    abstract var layoutId: Int

    abstract fun setupInjection(applicationComponent: ApplicationComponent)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupInjection(App.graph)
        initView()
        navigator.currentActivity = this
    }

    private fun initView() {
        setContentView(layoutInflater.inflate(layoutId, null))
        ButterKnife.bind(this)
    }
}