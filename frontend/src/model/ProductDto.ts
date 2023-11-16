import OwnerDto from "./OwnerDto";

export default interface ProductDto {
    name: string,
    id: number,
    amount: number,
    users: OwnerDto[]
}