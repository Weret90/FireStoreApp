package com.ermolaev_arkhip.firestoreapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ermolaev_arkhip.firestoreapp.databinding.ItemHealthDataBinding
import com.ermolaev_arkhip.firestoreapp.domain.model.HealthData

class HealthDataAdapter : RecyclerView.Adapter<Holder>() {

    private val list: MutableList<HealthData> = mutableListOf()

    fun refreshData(list: List<HealthData>) {
        this.list.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    fun updateData(healthData: HealthData) {
        list.add(healthData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemHealthDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}

class Holder(private val binding: ItemHealthDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(healthData: HealthData) {
        with(binding) {
            time.text = healthData.time
            pressure.text = healthData.pressure.toString()
            pulse.text = healthData.pulse.toString()
        }
    }
}