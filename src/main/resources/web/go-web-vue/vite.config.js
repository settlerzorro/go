import vue from '@vitejs/plugin-vue'

export default {
    base: './',
    plugins: [vue()],
    optimizeDeps: {
        include: ['schart.js']
    },
    server: {
        proxy: {
            // 选项写法
            '/api': {
                target: 'http://localhost:8888', // 所要代理的目标地址
                rewrite: path => path.replace(/^\/api/, ''), // 重写传过来的path路径，比如 `/api/index/1?id=10&name=zs`（注意:path路径最前面有斜杠（/），因此，正则匹配的时候不要忘了是斜杠（/）开头的；选项的 key 也是斜杠（/）开头的）
                changeOrigin: false,  // true/false, Default: false - changes the origin of the host header to the target URL
            }
        }
    }
}