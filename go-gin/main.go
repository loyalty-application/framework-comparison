package main

import (
	"github.com/framework-comparison/framework-comparison/controllers"
	"github.com/gin-gonic/gin"
)

func main() {
	r := gin.New()
	r.POST("/transaction", controllers.CreateTransaction)
	r.POST("/transaction/file", controllers.UploadTransactionFile)
	r.Run()
}
