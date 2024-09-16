window.onload = function(){

    /** Question One Code */
    function tipCalculator(bill){
        if(bill<50){
            return .2*bill;
        }
        else if(bill<=200){
            return .15*bill;
        }
        else{
            return .1*bill;
        }
    }
    
    function questionOne(){
        const bills = [120,45,280];
        let tips = new Array();
        let paidAmts = new Array();
        for(itr in bills){
            const tip = tipCalculator(bills[itr]);
            tips.push(tip);
            paidAmts.push(tip+bills[itr]);
        }
        return [tips,paidAmts];
    }
    
    const quesOneResult = questionOne();
    console.log("Question One Output");
    console.log(quesOneResult[0]);
    console.log(quesOneResult[1]);

    /** Question Two Code */
    function questionTwoA(){
        const days = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
        let trimmedDays = new Array();
        for(var day of days){
            let trimmedDay = day.toUpperCase().substring(0,3);
            trimmedDays.push(trimmedDay);
        }
        return trimmedDays;
    }
    
    const quesTwoAResult = questionTwoA();
    console.log("Question Two Output");
    console.log(quesTwoAResult); 
    
    function codeTheInput(charAtI){
        if(charAtI === 'a') return '4';
        else if(charAtI === 'e') return '3';
        else if(charAtI === 'i') return '1';
        else if(charAtI === 'o') return '0';
        else if(charAtI === 's') return '5';
        return charAtI;
    }
    
    function questionTwoB(){
        let userInput = prompt("Enter the input: ");
        const lengthOfInput = userInput.length;
        let result = "";
        for(let i=0;i<lengthOfInput;i++){
            let charAtI = userInput.charAt(i);
            const replacedChar = codeTheInput(charAtI);
            result = result+replacedChar;
        }
        return result;
    }
    
    const quesTwoBResult = questionTwoB();
    console.log(quesTwoBResult)


    /** Question Three Code */
    function calculateTotalWorth(warehouse){
        let totalWorth = 0;
        for(itr in warehouse){
            totalWorth += warehouse[itr].price;
        }
        return totalWorth;
    }

    function compareFn(a,b){
        if(a.price<b.price) return 1;
        else if(a.price>b.price) return -1;
        return 0;
    }

    function displayBlueObjects(warehouse){
        let blueObjects = new Array();
        for(itr in warehouse){
            if(warehouse[itr].color==="blue") blueObjects.push(warehouse[itr]);
        }
        return blueObjects;
    }

    function questionThree(){
        const shoes = [{
            type: "casual",
            color: "white",
            size: "9",
            price: 2000
        },{
            type: "formal",
            color: "black",
            size: "8",
            price: 1500
        }];
        const shirts = [{
            type: "casual",
            color: "navy blue",
            size: "M",
            price: 1100
        },{
            type: "formal",
            color: "blue",
            size: "L",
            price: 1500
        }];

        let warehouse = [...shoes,...shirts];

        console.log("Question Three Output");
        console.log(calculateTotalWorth(warehouse));
        warehouse.sort(compareFn);
        console.log(warehouse);
        console.log(displayBlueObjects(warehouse));
    }

    questionThree();

    /** Question Four Code */
    function questionFour(){
        let userInput = prompt("Enter the json string: ");
        let jsonObject = JSON.parse(userInput);
        for(itr in jsonObject){
            if(itr === "email") continue;
            let value = ""+jsonObject[itr];
            jsonObject[itr] = value.toUpperCase();
        }
        console.log("Question Four Output");
        console.log(jsonObject);
        const {email, ...modifiedObject} = jsonObject;
        console.log(JSON.stringify(modifiedObject));
    }

    questionFour();

    /** Question Five Code */
    function getAllKeysAndValues(object,values){
        var keys = [];
        for (let key in object) {
            if (object.hasOwnProperty(key)) {
                keys.push(key);
                if (typeof object[key] === 'object') {
                    let nestedKeys = getAllKeysAndValues(object[key],values);
                    keys = keys.concat(nestedKeys);
                }
                else{
                    values.push(object[key]);
                }
            }
        }
        return keys;
    }

    function questionFive(){
        player = {
            firstName: "Leo",
            lastName: "Messi",
            address: {
              country: "Spain",
              city: "Barcelona",
            },
            careerInfo: {
                fcBarcelona: {
                    appearances: 780,
                    goals: {
                        premierLeagueGoals: 590,
                        championsLeagueGoals: 50,
                    },
                },
            },
        } 
        var values = [];
        let keys = getAllKeysAndValues(player,values);
        console.log("Question Five Output");
        console.log(keys);
        console.log(values);
    }

    questionFive();
};