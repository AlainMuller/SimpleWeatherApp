package com.designhumanist.faragojanos.weaterforecast.ui.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.designhumanist.faragojanos.weaterforecast.R
import com.designhumanist.faragojanos.weaterforecast.model.CurrentWeather
import com.designhumanist.faragojanos.weaterforecast.ui.inflate
import com.designhumanist.faragojanos.weaterforecast.ui.navigation.Navigate
import kotlinx.android.synthetic.main.view_city.view.*

class CityListAdapter: RecyclerView.Adapter<CityListAdapter.CityViewHolder>() {

    private val items: MutableList<CurrentWeather> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CityListAdapter.CityViewHolder =
            CityViewHolder(parent!!)

    override fun onBindViewHolder(holder: CityViewHolder?, position: Int) {
        holder as CityViewHolder
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun add(item: CurrentWeather) {
        items.add(item)
        notifyDataSetChanged()
    }

    fun addAll(items: List<CurrentWeather>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
    }

    inner class CityViewHolder(parent : ViewGroup)
        : RecyclerView.ViewHolder(parent.inflate(R.layout.view_city)) {

        fun bind(item: CurrentWeather) = with(itemView) {
            cityNameTextView.text = item.name
            temperatureTextView.text = "${item.main.temp_min}/${item.main.temp_max}"
            Glide.with(this)
                    .load("http://openweathermap.org/img/w/${item.weather[0].icon}.png")
                    .into(weatherImageView)

            itemView.setOnClickListener {
                Navigate(context).toCity(item.name)
            }
        }
    }
}
