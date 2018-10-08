package me.ekhaled1619.activitytracker

import android.app.Application
import android.location.Location
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult

class TrackingViewModel(application: Application) : AndroidViewModel(application) {

    private val locationUpdate = MutableLiveData<Location>()

//    private val localBroadcastManager by lazy {
//        LocalBroadcastManager.getInstance(application)
//    }

    private val locationCallback by lazy {
        object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations) {
//                    val intent = Intent(LocationService.ACTION_LOCATION)
//                    intent.putExtra(LocationService.EXTRA_LOCATION, location)
//                    localBroadcastManager.sendBroadcast(intent)
                    locationUpdate.postValue(location)
                }
            }
        }
    }

    fun getLocationCallback(): LocationCallback {
        return locationCallback
    }

    fun getLocationUpdate(): LiveData<Location> {
        return locationUpdate
    }

    /*override fun onCleared() {
        super.onCleared()
    }*/
}