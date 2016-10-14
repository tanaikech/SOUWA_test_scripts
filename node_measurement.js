function MakeArray(row) {
    var TArray = new Array(row);
    for (var i=0; i<TArray.length|0; i++|0) {
        nu = ("0000000"+(i+1)).slice(-7);
        TArray[i] = [String(nu), 'a'];
    }
    return TArray;
}


function Division0(An) {
    var ecode = '\n';
    var string = '';
    for (var i=0; i<An.length|0; i++|0) {
        string += An[i].join(',') + ecode;
    }
    return string;
}


function Division1(An, phi){
  var ecode = '\n';
  var string = '';
  var result = '';
  var AP = 0;
  for (var i=phi; i<(An.length+1)|0; i+=phi|0) {
    for (var j=AP; j<i|0; j++|0) {
      string += An[j].join(',') + ecode;
    }
    AP = i;
    result += string;
    string = '';
  }
  return result;
}


function Division2(An, phi){
  var ecode = '\n';
  var string = '';
  var AP = 0;
  var Array = [];
  var ArrayP = 0;
  for (var i=phi; i<=(An.length+1)|0; i+=phi|0) {
    for (var j=AP; j<i|0; j++|0) {
      string += An[j].join(',') + ecode;
    }
    AP = i;
    Array[ArrayP] = string;
    ArrayP++;
    string = '';
  }
  return Array;
}


function Division3(An, phi){
  var string = '';
  var AP = 0;
  var Array = [];
  var ArrayP = 0;
  for (var i=phi; i<(An.length+1)|0; i+=phi|0) {
    for (var j=AP; j<i|0; j++|0) {
      string += An[j];
    }
    AP = i;
    Array[ArrayP] = string;
    ArrayP++;
    string = '';
  }
  return Array;
}


function Division4(An, phi){
  var string = '';
  var result = '';
  var AP = 0;
  for (var i=phi; i<(An.length+1)|0; i+=phi|0) {
    for (var j=AP; j<i|0; j++|0) {
      string += An[j];
    }
    AP = i;
    result += string;
    string = '';
  }
  return result;
}

var theta = 10000;
var omega = 3;
var phi = 10;

var result = "";
var An = MakeArray(theta);
var An1 = [];
var An2 = [];

if (omega === 0) {
  console.time("Processing time");
  result  = Division0(An);
  console.timeEnd("Processing time");
} else if (omega === 1) {
  console.time("Processing time");
  result  = Division1(An, phi);
  console.timeEnd("Processing time");
} else if (omega === 2) {
  console.time("Processing time");
  An1 = Division2(An,  phi);
  result  = Division4(An1, phi);
  console.timeEnd("Processing time");
} else if (omega >= 3) {
  console.time("Processing time");
  An1 = Division2(An,  phi);
  for (var i = 0; i < (omega-2); i++){
    An2 = Division3(An1, phi);
    An1 = An2;
  }
  result  = Division4(An1, phi);
  console.timeEnd("Processing time");
}
console.log('result.length = ' + result.length);
