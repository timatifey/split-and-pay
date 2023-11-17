import {AxiosResponse} from "axios";
import {instance} from "../configuration/api.config";
import RoomDto from "../model/RoomDto";
import CreateRoomRequest from "../model/CreateRoomRequest";

class RoomService {
    getRooms(): Promise<AxiosResponse<Array<RoomDto>>> {
        return instance.get("/api/rooms/")
    }

    createRoom(createRoomRequest: CreateRoomRequest): Promise<AxiosResponse<RoomDto>> {
        return instance.post("/api/rooms/", createRoomRequest)
    }

    connectToRoom(roomNumber: string): Promise<AxiosResponse<RoomDto>> {
        return instance.get(`/api/rooms/${roomNumber}/connect`)
    }

    createNew(roomName: string): Promise<AxiosResponse<RoomDto>> {
        return instance.post('/api/rooms/', {"name": roomName})
    }

    deleteRoom(roomNumber: number): Promise<AxiosResponse<Array<RoomDto>>> {
        return instance.delete(`/api/rooms/${roomNumber}/disconnect`)
    }

    getRoom(roomId: string): Promise<AxiosResponse<RoomDto>> {
        return instance.get(`/api/rooms/${roomId}`)
    }

    addUserToProduct(roomId: string, productId: number): Promise<AxiosResponse<RoomDto>> {
        return instance.post(`/api/rooms/${roomId}/addUserToProduct`, {
            "productId": productId,
            "userId": localStorage.getItem('userId')
        })
    }

    deleteUserFromProduct(roomId: string, productId: number): Promise<AxiosResponse<RoomDto>> {
        return instance.post(`/api/rooms/${roomId}/deleteUserFromProduct`, {
            "productId": productId,
            "userId": localStorage.getItem('userId')
        })
    }

    addProduct(roomId: string, productName: string, cost: number): Promise<AxiosResponse<RoomDto>> {
        return instance.post(`/api/rooms/${roomId}/addProduct`, {
            "name": productName,
            "amount": cost
        })
    }
}

const roomService = new RoomService();
export default roomService;