import request from "./request";

export function login(data){

    return request.post(

        "/user/login",

        data

    )

}