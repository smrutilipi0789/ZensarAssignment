

import androidx.lifecycle.LiveData
import androidx.paging.*
import androidx.paging.rxjava2.observable
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

/**
 * repository class to manage the data flow and map it if needed
 */
@ExperimentalPagingApi
class DoggoImagesRepository(
    val doggoApiService: DoggoApiService = RemoteInjector.injectDoggoApiService(),

) {

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 20

        //get doggo repository instance
        fun getInstance() = DoggoImagesRepository()
    }


    //for live data users
    fun letDoggoImagesLiveData(pagingConfig: PagingConfig = getDefaultPageConfig()): LiveData<PagingData<DogData>> {
        return Pager(
            config = pagingConfig,
        ).liveData
    }

    /**
     * let's define page size, page size is the only required param, rest is optional
     */
    fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }



}