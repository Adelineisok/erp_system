import request from'../utils/request'

export function getProductList(){

    returnrequest({

        url:'/product/list',

        method:'get'

    })

}