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
    lateinit var remoteViewModel: DogViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main);

        // bind RecyclerView
        val recyclerView = activityMainBinding?.rvDoggoLoader
        loadBar = activityMainBinding!!.loadBar
      //  recyclerView!!.setLayoutManager(LinearLayoutManager(this))
        recyclerView!!.setHasFixedSize(true)

        ///init the View Modelvi
        mainViewModel = ViewModelProders.of(this).get(DogViewModel::class.java)

        //init the Custom adataper
        mDogAdapter = DogItemAdapter()
        //set the CustomAdapter
        recyclerView.setAdapter(mDogAdapter)

      //  getAllDev()
        fetchDogImages()
    }

    private fun getAllDev() {
        ///get the list of dev from api response
        mainViewModel!!.allDogCollection.observe(this,
            Observer<List<Any>> { dogList ->
                ///if any thing chnage the update the UI
                mDogAdapter?.setDogList(dogList as ArrayList<DogData>)
                loadBar?.visibility = View.GONE
            })
    }
    private fun fetchDogImages(){
        remoteViewModel.fetchDoggoImagesObservable().subscribe {
            lifecycleScope.launch {
                mDogAdapter.submitData(it)}

        }

}

