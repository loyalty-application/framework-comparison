const express = require('express')
const {readTransactions} = require('../controllers/transactions')
const inventoryRouter = express.Router()

inventoryRouter.route('/').post(readTransactions)
