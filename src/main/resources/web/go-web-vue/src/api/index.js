import request from '../utils/request';

// 登录接口
export const loginApi = message => {
    return request({
        url: '/api/login',
        method: 'post',
        params: message
    })
}

// 获取用户信息接口
export const getUserInfo = () => {
    return request({
        url: '/api/loginSuccess',
        method: 'get',
        params: {}
    })
}

// 获取航班信息接口
export const getAirList = (query) => {
    return request({
        url: '/api/air/getAirList',
        method: 'post',
        data: query
    })
}

// 新增航班信息接口
export const insertAir = (data) => {
    return request({
        url: '/api/air/insertAir',
        method: 'post',
        data: data
    })
}

// 修改航班信息接口
export const updateAir = (data) => {
    return request({
        url: '/api/air/updateAir',
        method: 'post',
        data: data
    })
}

// 删除航班信息接口
export const deleteAir = (id) => {
    return request({
        url: '/api/air/deleteAir?id='+id,
        method: 'delete',
        data: {}
    })
}

// 删除航班评论信息接口
export const deleteAirComment = (id) => {
    return request({
        url: '/api/air/deleteComment?id='+id,
        method: 'delete',
        data: {}
    })
}

// 获取游轮信息接口
export const getShipList = (query) => {
    return request({
        url: '/api/ship/getShipList',
        method: 'post',
        data: query
    })
}

// 新增游轮信息接口
export const insertShip = (data) => {
    return request({
        url: '/api/ship/insertShip',
        method: 'post',
        data: data
    })
}

// 修改游轮信息接口
export const updateShip = (data) => {
    return request({
        url: '/api/ship/updateShip',
        method: 'post',
        data: data
    })
}

// 删除游轮信息接口
export const deleteShip = (id) => {
    return request({
        url: '/api/ship/deleteShip?id='+id,
        method: 'delete',
        data: {}
    })
}

// 删除游轮评论信息接口
export const deleteShipComment = (id) => {
    return request({
        url: '/api/ship/deleteComment?id='+id,
        method: 'delete',
        data: {}
    })
}

// 获取火车信息接口
export const getTrainList = (query) => {
    return request({
        url: '/api/train/getTicketList',
        method: 'post',
        data: query
    })
}

// 新增火车信息接口
export const insertTrain = (data) => {
    return request({
        url: '/api/train/insertTrain',
        method: 'post',
        data: data
    })
}

// 修改火车信息接口
export const updateTrain = (data) => {
    return request({
        url: '/api/train/updateTrain',
        method: 'post',
        data: data
    })
}

// 删除火车信息接口
export const deleteTrain = (id) => {
    return request({
        url: '/api/train/deleteTrain?id='+id,
        method: 'delete',
        data: {}
    })
}

// 删除火车评论信息接口
export const deleteTrainComment = (id) => {
    return request({
        url: '/api/train/deleteComment?id='+id,
        method: 'delete',
        data: {}
    })
}

// 获取汽车信息接口
export const getBusList = (query) => {
    return request({
        url: '/api/bus/getBusList',
        method: 'post',
        data: query
    })
}

// 新增汽车信息接口
export const insertBus = (data) => {
    return request({
        url: '/api/bus/insertBus',
        method: 'post',
        data: data
    })
}

// 修改汽车信息接口
export const updateBus = (data) => {
    return request({
        url: '/api/bus/updateBus',
        method: 'post',
        data: data
    })
}

// 删除汽车信息接口
export const deleteBus = (id) => {
    return request({
        url: '/api/bus/deleteBus?id='+id,
        method: 'delete',
        data: {}
    })
}

// 删除汽车评论信息接口
export const deleteBusComment = (id) => {
    return request({
        url: '/api/bus/deleteComment?id='+id,
        method: 'delete',
        data: {}
    })
}
