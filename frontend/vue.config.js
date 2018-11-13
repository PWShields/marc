module.exports = {
    outputDir: 'target/dist',
    assetsDir: 'static',
    devServer: {
        proxy: {
            '/download/*': {
                target: 'http://localhost:8080',
                ws: true,
                changeOrigin: true
            }
        }
    }
}
