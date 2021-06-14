package com.example.dogapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogapp.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    internal var activityMainBinding: ActivityMainBinding?= null
    internal var loadBar : ProgressBar? = null
    var mainViewModel: DogViewModel? = null
    private var mDogAdapter: DogItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main);

        // bind RecyclerView
        val recyclerView = activityMainBinding?.rvDoggoLoader
        loadBar = activityMainBinding!!.loadBar
        recyclerView!!.setHasFixedSize(true)

        ///init the View Model
        mainViewModel = ViewModelProders.of(this).get(DogViewModel::class.java)

        //init the Custom adataper
        mDogAdapter = DogItemAdapter()
        recyclerView.adapter = mDogAdapter
        fetchDoggoImagesLiveData()
    }

    private fun fetchDoggoImagesLiveData() {
        mainViewModel.fetchDoggoImagesLiveData().observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                mDogAdapter.submitData(it)
            }
        })
    }


    }



