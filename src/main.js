var getJSON = function(url, callback) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);
    xhr.responseType = 'json';
    xhr.onload = function() {
      var status = xhr.status;
      if (status === 200) {
        callback(xhr.response);
      } else {
        console.error(status);
      }
    };
    xhr.send();
};

window.onload = function(e) {
    alert('code');
    document.getElementById('calculator').onsubmit = function() {
        getJSON('https://raw.githubusercontent.com/Seggan/SFCalc/gh-pages/src/items.json', function(items) {
            alert(items[0].name);
        });
    };
};