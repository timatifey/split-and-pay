import {AxiosResponse} from "axios";
import UserDto from "../model/UserDto";
import {instance} from "../configuration/api.config";

class UserService {
    createUser(userDto: UserDto): Promise<AxiosResponse<{ id: string }>> {
        return instance.post<{ id: string }>("/api/user/", userDto)
    }

    generateRandomName(): Promise<AxiosResponse<UserDto>> {
        return instance.get("/api/misc/randomName")
    }
}

const userService = new UserService();
export default userService;