package com.designhumanist.faragojanos.weaterforecast.ui.citydetails.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.designhumanist.faragojanos.weaterforecast.R
import com.designhumanist.faragojanos.weaterforecast.model.ForecastData
import com.designhumanist.faragojanos.weaterforecast.ui.inflate
import kotlinx.android.synthetic.main.view_forecast.view.*
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat



class ForecastAdapter: RecyclerView.Adapter<ForecastAdapter.ForeCastViewHolder>() {

    val items: MutableList<ForecastData> = mutableListOf()

    override fun onBindViewHolder(holder: ForeCastViewHolder?, position: Int) {
        holder as ForeCastViewHolder
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ForeCastViewHolder=
            ForeCastViewHolder(parent!!)

    override fun getItemCount(): Int = items.size

    fun addForecast(forecast: List<ForecastData>) {
        items.clear()
        items.addAll(forecast)
        notifyDataSetChanged()
    }

    inner class ForeCastViewHolder(parent : ViewGroup)
        : RecyclerView.ViewHolder(parent.inflate(R.layout.view_forecast)) {

        fun bind(item: ForecastData) = with(itemView) {
            val timeZone = DateTimeZone.forID("Europe/Paris")
            val dateTime = DateTime(item.dt, timeZone)
            val formatter = DateTimeFormat.forPattern("EEEE").withLocale(java.util.Locale.ENGLISH)
            val dayOfWeekName = formatter.print(dateTime)

            dayTextView.text = dayOfWeekName
            Glide.with(this)
                    .load("http://openweathermap.org/img/w/${item.weather.icon}.png")
                    .into(iconImageView)
            mainTextView.text = item.weather.main
            meanTempTextView.text = item.temp.day.toString()

        }

    }
}