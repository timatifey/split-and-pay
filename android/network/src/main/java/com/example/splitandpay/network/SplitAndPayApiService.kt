package com.example.splitandpay.network

import com.example.splitandpay.network.model.CheckData
import com.example.splitandpay.network.model.Product
import com.example.splitandpay.network.model.UserToProduct
import com.example.splitandpay.network.model.RandomName
import com.example.splitandpay.network.model.Room
import com.example.splitandpay.network.model.RoomDetails
import com.example.splitandpay.network.model.RoomId
import com.example.splitandpay.network.model.RoomName
import com.example.splitandpay.network.model.UserId
import com.example.splitandpay.network.model.Username

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * @author timatifey
 */
public interface SplitAndPayApiService {

    // User
    @POST("api/user/")
    suspend fun createUser(
        @Body username: Username,
    ): Response<UserId>

    @PUT("api/user/")
    suspend fun updateUser(
        @Header("userId") userId: String,
        @Body username: Username,
    ): Response<UserId>

    // Room
    @POST("api/rooms/")
    suspend fun createRoom(
        @Header("userId") userId: String,
        @Body roomName: RoomName,
    ): Response<RoomId>

    @GET("api/rooms/")
    suspend fun getRooms(
        @Header("userId") userId: String,
    ): Response<List<Room>>

    @GET("api/rooms/{roomId}")
    suspend fun getRoomDetails(
        @Header("userId") userId: String,
        @Path("roomId") roomId: Long,
    ): Response<RoomDetails>

    @GET("api/rooms/{roomId}/connect/")
    suspend fun connectToRoom(
        @Header("userId") userId: String,
        @Path("roomId") roomId: Long,
    ): Response<RoomDetails>

    // Receipt
    @POST("api/rooms/{roomId}/addProduct")
    suspend fun addProduct(
        @Header("userId") userId: String,
        @Path("roomId") roomId: Long,
        @Body product: Product,
    ): Response<RoomDetails>

    @POST("api/rooms/{roomId}/addUserToProduct")
    suspend fun addUserToProduct(
        @Header("userId") userId: String,
        @Path("roomId") roomId: Long,
        @Body userToProduct: UserToProduct,
    ): Response<RoomDetails>

    @POST("api/rooms/{roomId}/addProductFromCheck/")
    suspend fun addProductFromCheck(
        @Header("userId") userId: String,
        @Path("roomId") roomId: Long,
        @Body checkData: CheckData,
    ): Response<RoomDetails>

    // Misc
    @GET("api/misc/randomName/")
    suspend fun getRandomName(): Response<RandomName>
}
