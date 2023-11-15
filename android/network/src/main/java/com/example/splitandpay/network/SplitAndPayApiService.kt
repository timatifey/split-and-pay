package com.example.splitandpay.network

import com.example.splitandpay.network.model.RandomName
import com.example.splitandpay.network.model.Room
import com.example.splitandpay.network.model.RoomDetails
import com.example.splitandpay.network.model.RoomId
import com.example.splitandpay.network.model.UserId

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

/**
 * @author timatifey
 */
public interface SplitAndPayApiService {

    // User
    @POST("api/user")
    suspend fun createUser(
        @Field("username") username: String,
    ): Response<UserId>

    @PUT("api/user")
    suspend fun updateUser(
        @Field("username") username: String,
    ): Response<UserId>

    // Room
    @POST("api/rooms")
    suspend fun createRoom(
        @Header("userId") userId: String,
        @Field("name") roomName: String,
    ): Response<RoomId>

    @GET("api/rooms")
    suspend fun getRooms(
        @Header("userId") userId: String,
    ): Response<List<Room>>

    @GET("api/rooms/{roomId}")
    suspend fun getRoomDetails(
        @Header("userId") userId: String,
        @Query("roomId") roomId: Long,
    ): Response<RoomDetails>

    // Utils
    @GET("api/misc/randomName")
    suspend fun getRandomName(): Response<RandomName>
}
