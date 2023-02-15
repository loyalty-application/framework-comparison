package main

import (
	"example/go-rest-api/controllers"

	"github.com/gin-gonic/gin"
)

func main() {
	r := gin.New()
	r.POST("/UploadCSVFile/:id", controllers.UploadCSVFile)
	r.Run()
}
