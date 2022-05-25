package com.ermolaev_arkhip.firestoreapp.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.ermolaev_arkhip.firestoreapp.R
import com.ermolaev_arkhip.firestoreapp.databinding.ActivityMainBinding
import com.ermolaev_arkhip.firestoreapp.domain.model.HealthData
import com.ermolaev_arkhip.firestoreapp.domain.utils.GetState
import com.ermolaev_arkhip.firestoreapp.domain.utils.LoadState
import com.ermolaev_arkhip.firestoreapp.presentation.adapter.HealthDataAdapter
import com.ermolaev_arkhip.firestoreapp.presentation.viewmodel.MainViewModel
import com.ermolaev_arkhip.firestoreapp.presentation.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import java.util.*
import javax.inject.Inject
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

    private val adapter by lazy {
        HealthDataAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = adapter

        viewModel.getList.observe(this) { state ->
            binding.progressBar.isGone = true
            when (state) {
                is GetState.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is GetState.Error -> {
                    Snackbar
                        .make(binding.root, state.throwable.message.toString(), Snackbar.LENGTH_INDEFINITE)
                        .setAction(getString(R.string.reload)) {
                            viewModel.getListOfHealthData()
                        }.show()
                }
                is GetState.Success -> {
                    adapter.refreshData(state.list)
                }
            }
        }

        viewModel.loadData.observe(this) { state ->
            when (state) {
                is LoadState.Error -> {
                    Toast.makeText(this, state.throwable.message, Toast.LENGTH_SHORT).show()
                }
                is LoadState.Success -> {
                    //запись успешно сохранена
                }
            }
        }

        binding.addHealthData.setOnClickListener {
            val healthData = createNewHealthData()
            viewModel.saveHealthData(healthData)
            adapter.updateData(healthData)
        }
    }

    private fun createNewHealthData(): HealthData {
        val pressure = Random.nextInt(100, 140)
        val pulse = Random.nextInt(60, 90)
        val time = Calendar.getInstance().time
        return HealthData(
            time = time.toString(),
            pressure = pressure,
            pulse = pulse
        )
    }
}