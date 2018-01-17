package es.jarroyo.daggerandkotlin.ui.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.jarroyo.daggerandkotlin.R
import es.jarroyo.daggerandkotlin.ui.base.loadUrl
import es.jarroyo.daggerandkotlin.ui.home.HomeDisplayModel
import kotlinx.android.synthetic.main.item_home.view.*

/**
 * Created by javierarroyo on 17/1/18.
 */
class HomeAdapter(private val homeList: HomeDisplayModel,
                  private val listener : (Int) -> Unit): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    override fun getItemCount(): Int = homeList.size()

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val homeData = homeList.get(position)
        holder?.bind(homeData, position, listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_home, parent, false)
        return ViewHolder(itemView)    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dataHome: HomeDisplayModel.DataHomeDisplayModel, position: Int, listener: (Int) -> Unit) = with(itemView) {
            home_title.text = dataHome.title
            home_image.loadUrl(dataHome.thumbnail)
            setOnClickListener { listener(position) }
        }
    }

}