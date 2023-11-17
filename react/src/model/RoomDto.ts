import OwnerDto from "./OwnerDto";
import ProductDto from "./ProductDto";

export default interface RoomDto {
    id: number,
    name: string,
    owner: OwnerDto,
    createdAt: string,
    users: OwnerDto[] | null,
    receipt: ProductDto[] | null,
    totalSum: number | null
}