import request from '../utils/request';

// 登录接口
export const loginApi = message => {
    return request({
        url: '/login',
        method: 'post',
        params: message
    })
}

// 注册接口
export const registerApi = message => {
    return request({
        url: '/register',
        method: 'post',
        data: message
    })
}

// 退出登录接口
export const logoutApi = () => {
    return request({
        url: '/logout',
        method: 'get',
        params: {}
    })
}

// 获取用户信息接口
export const getUserInfo = () => {
    return request({
        url: '/loginSuccess',
        method: 'get',
        params: {}
    })
}

// 获取航班信息接口
export const getAirList = (query) => {
    return request({
        url: '/air/getAirList',
        method: 'post',
        params: query,
        data: query
    })
}

// 新增航班信息接口
export const insertAir = (data) => {
    return request({
        url: '/air/insertAir',
        method: 'post',
        data: data
    })
}

// 修改航班信息接口
export const updateAir = (data) => {
    return request({
        url: '/air/updateAir',
        method: 'post',
        data: data
    })
}

// 删除航班信息接口
export const deleteAir = (id) => {
    return request({
        url: '/air/deleteAir?id='+id,
        method: 'delete',
        data: {}
    })
}

// 评论航班
export const commentAir = (data) => {
    return request({
        url: '/air/insertComment',
        method: 'post',
        data: data
    })
}

// 删除航班评论信息接口
export const deleteAirComment = (id) => {
    return request({
        url: '/air/deleteComment?id='+id,
        method: 'delete',
        data: {}
    })
}

// 收藏航班
export const likeAir = (data) => {
    return request({
        url: '/air/like',
        method: 'get',
        params: data,
        data: {}
    })
}

// 取消收藏航班
export const dislikeAir = (id) => {
    return request({
        url: '/air/dislike?id='+id,
        method: 'get',
        data: {}
    })
}

// 获取游轮信息接口
export const getShipList = (query) => {
    return request({
        url: '/ship/getShipList',
        method: 'post',
        params: query,
        data: query
    })
}

// 新增游轮信息接口
export const insertShip = (data) => {
    return request({
        url: '/ship/insertShip',
        method: 'post',
        data: data
    })
}

// 修改游轮信息接口
export const updateShip = (data) => {
    return request({
        url: '/ship/updateShip',
        method: 'post',
        data: data
    })
}

// 删除游轮信息接口
export const deleteShip = (id) => {
    return request({
        url: '/ship/deleteShip?id='+id,
        method: 'delete',
        data: {}
    })
}

// 评论游轮
export const commentShip = (data) => {
    return request({
        url: '/ship/insertComment',
        method: 'post',
        data: data
    })
}

// 删除游轮评论信息接口
export const deleteShipComment = (id) => {
    return request({
        url: '/ship/deleteComment?id='+id,
        method: 'delete',
        data: {}
    })
}

// 收藏游轮
export const likeShip = (data) => {
    return request({
        url: '/ship/like',
        method: 'get',
        params: data,
        data: {}
    })
}

// 取消收藏游轮
export const dislikeShip = (id) => {
    return request({
        url: '/ship/dislike?id='+id,
        method: 'get',
        data: {}
    })
}

// 获取火车信息接口
export const getTrainList = (query) => {
    return request({
        url: '/train/getTicketList',
        method: 'post',
        params: query,
        data: query
    })
}

// 新增火车信息接口
export const insertTrain = (data) => {
    return request({
        url: '/train/insertTrain',
        method: 'post',
        data: data
    })
}

// 修改火车信息接口
export const updateTrain = (data) => {
    return request({
        url: '/train/updateTrain',
        method: 'post',
        data: data
    })
}

// 删除火车信息接口
export const deleteTrain = (id) => {
    return request({
        url: '/train/deleteTrain?id='+id,
        method: 'delete',
        data: {}
    })
}

// 评论火车
export const commentTrain = (data) => {
    return request({
        url: '/train/insertComment',
        method: 'post',
        data: data
    })
}

// 删除火车评论信息接口
export const deleteTrainComment = (id) => {
    return request({
        url: '/train/deleteComment?id='+id,
        method: 'delete',
        data: {}
    })
}

// 收藏火车
export const likeTrain = (data) => {
    return request({
        url: '/train/like',
        method: 'get',
        params: data,
        data: {}
    })
}

// 取消收藏火车
export const dislikeTrain = (id) => {
    return request({
        url: '/train/dislike?id='+id,
        method: 'get',
        data: {}
    })
}

// 获取汽车信息接口
export const getBusList = (query) => {
    return request({
        url: '/bus/getBusList',
        method: 'post',
        params: query,
        data: query
    })
}

// 新增汽车信息接口
export const insertBus = (data) => {
    return request({
        url: '/bus/insertBus',
        method: 'post',
        data: data
    })
}

// 修改汽车信息接口
export const updateBus = (data) => {
    return request({
        url: '/bus/updateBus',
        method: 'post',
        data: data
    })
}

// 删除汽车信息接口
export const deleteBus = (id) => {
    return request({
        url: '/bus/deleteBus?id='+id,
        method: 'delete',
        data: {}
    })
}

// 评论汽车
export const commentBus = (data) => {
    return request({
        url: '/bus/insertComment',
        method: 'post',
        data: data
    })
}

// 删除汽车评论信息接口
export const deleteBusComment = (id) => {
    return request({
        url: '/bus/deleteComment?id='+id,
        method: 'delete',
        data: {}
    })
}

// 收藏汽车
export const likeBus = (data) => {
    return request({
        url: '/bus/like',
        method: 'get',
        params: data,
        data: {}
    })
}

// 取消收藏汽车
export const dislikeBus = (id) => {
    return request({
        url: '/bus/dislike?id='+id,
        method: 'get',
        data: {}
    })
}

// 获取广告
export const getAdList = () => {
    return request({
        url: '/advert/getAdList',
        method: 'post',
        data: {}
    })
}

// 新增广告
export const insertAdvert = (params, formData) => {
    return request({
        url: '/advert/insertAdvert',
        method: 'post',
        headers:{"Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"},//设置响应投
        data: formData,
        params: params
    })
}

// 删除广告接口
export const deleteAdvert = (id) => {
    return request({
        url: '/advert/deleteAdvert?id='+id,
        method: 'delete',
        data: {}
    })
}

// 激活广告接口
export const showAd = (id) => {
    return request({
        url: '/advert/showAd?id='+id,
        method: 'post',
        data: {}
    })
}

// 隐藏广告接口
export const hideAd = (id) => {
    return request({
        url: '/advert/hideAd?id='+id,
        method: 'post',
        data: {}
    })
}

// 获取推荐接口
export const getTransportList = (params) => {
    return request({
        url: '/transportApi/getTransportList',
        method: 'get',
        params: params,
        data: {}
    })
}
