package com.example.buse_ui.data

import com.example.buse_ui.data.models.MapsData
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MapRepoImpl(
    private val api: com.example.buse_ui.data.Api
): com.example.buse_ui.data.MapsRepository {

    override suspend fun locationQuery(
        origin: String,
        destination: String,
        key: String
    ): Flow<com.example.buse_ui.data.Response<MapsData>> {
        return flow {
            val locationQueryData = try {
                api.locationQuery(origin, destination, key)
            }catch (e:Exception){
                e.printStackTrace()
                emit(com.example.buse_ui.data.Response.Error(message = "Problem Loading"))
                return@flow
            }
            emit(com.example.buse_ui.data.Response.Success(locationQueryData))
        }
    }

}