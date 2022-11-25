const { createProxyMiddleware } = require("http-proxy-middleware")

module.exports = app => {
    app.use(
        createProxyMiddleware('/datoHora/datesupload',
            {
                target: 'http://localhost:8080',
                changeOrigin:  true
            })
    )
}