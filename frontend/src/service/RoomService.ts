import {AxiosResponse} from "axios";
import {instance} from "../configuration/api.config";
import RoomDto from "../model/RoomDto";
import CreateRoomRequest from "../model/CreateRoomRequest";

class RoomService {
    getRooms(): Promise<AxiosResponse<Array<RoomDto>>> {
        return instance.get("/api/rooms/", {headers: {'userId': localStorage.getItem('userId')}})
    }

    createRoom(createRoomRequest: CreateRoomRequest): Promise<AxiosResponse<RoomDto>> {
        return instance.post("/api/rooms/", createRoomRequest)
    }
}

const roomService = new RoomService();
export default roomService;