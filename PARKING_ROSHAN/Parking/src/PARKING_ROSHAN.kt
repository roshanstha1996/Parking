class Car(val licensePlate: String, var timeRemaining: Int) {

}

class ParkingLot {
    var name: String = "12 Fair View Don Mills Parking"
    var numSpaces: Int = 4
    var hourlyRate: Double = 4.5
    var balance: Double = 0.0
    var parkedCarList:MutableList<Car>? = mutableListOf()

    fun findCar(licensePlate: String): Boolean{
        for(car:Car in this.parkedCarList!!){
            if(car.licensePlate == licensePlate){
                return true
            }
        }
        return false
    }

    fun parkCar(licensePlate: String, hoursRequested: Int, amountPaid: Double){
        //check if there is available spaces for parking
        if(this.numSpaces < 1){
            return println("ERROR: The Parking  Lot is full for now!! \n")
        }else {
            //first check if there are cars in parking lot list
            if (this.parkedCarList == null) {
                return println("There is available list")
            } else {
                //now check if the car is parked in the parking lot or not
                if (findCar(licensePlate)){
                    return println("ERROR: Car (License No.): ${licensePlate} is already parked in the parking lot\n")
                }
                else{
                    val toBeAmount: Double = hoursRequested * this.hourlyRate

                    //check if the given amount is enough by calculation (hoursRequest * hourlyRate)
                    if(toBeAmount > amountPaid){
                        return println("ERROR: Insufficient Amount Paid for Car ${licensePlate}! Parking Cost is $${toBeAmount}. You paid $${amountPaid} only!! \n")
                    }
                    parkedCarList!!.add(Car(licensePlate, hoursRequested))
                    this.numSpaces = this.numSpaces - 1
                    this.balance = this.balance + toBeAmount

                    return println("SUCCESS: Car ${licensePlate} is parked!!")
                }
            }
        }
    }

    fun passTime(hours: Int) {

        if(hours < 1){
            println("ERROR: Invalid hours value.")
        }else{
            println("Passing Time... \n")

            for(cars:Car in parkedCarList!!){
                cars.timeRemaining = cars.timeRemaining - hours

                if(cars.timeRemaining < 1){
                    cars.timeRemaining = 0
                }
            }
        }
    }

    override fun toString(): String {
        println("----DEMO PARKING LOT INFORMATION----")
        println("Parking Lot Name: ${this.name}")
        println("Space Available: ${this.numSpaces}")
        println("Hourly Rate: $${this.hourlyRate}")
        println("Total amount of fees collected (balance): $${this.balance}")
        if (parkedCarList == null){
            println("Number of parked car: 0\n")
        }
        else{
            println("Number of parked car: ${parkedCarList!!.size}\n")

            for(cars:Car in parkedCarList!!){
                println("Car ${cars.licensePlate}, Time Remaining: ${cars.timeRemaining}")
            }
        }
        return ""
    }

}

fun main(){
    val parkingLot = ParkingLot()
    println(parkingLot)

    //parking 3 cars in the lot
    parkingLot.parkCar("CHEVY123", 6, 30.0)
    parkingLot.parkCar("TESLA123", 4, 30.0)
    parkingLot.parkCar("MUSTANG101", 8, 40.0)

    //check if the same car can be added in the lot
    parkingLot.parkCar("CHEVY123", 7, 30.0)
//    println(parkingLot)

    //adding car in the lot with insufficient amount
    parkingLot.parkCar("BUGATTI1", 8, 30.0)
    println(parkingLot)

    //adding another car in the lot to check if parking is available
    parkingLot.parkCar("BUGATTI1", 8, 36.0)

    //adding another car in the lot to check if parking is available
    parkingLot.parkCar("CIVIC111", 8, 36.0)
    println(parkingLot)

    //passing time
    parkingLot.passTime(3)
    println(parkingLot)

    //passing time
    parkingLot.passTime(4)
    println(parkingLot)

    //passing time
    parkingLot.passTime(2)
    println(parkingLot)
}


