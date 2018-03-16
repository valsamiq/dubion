(function() {
  // Tutorial: https://medium.com/@PatrykZabielski/how-to-make-multi-layered-parallax-illustration-with-css-javascript-2b56883c3f27
  window.addEventListener('scroll', function(event) {
    var depth, i, layer, layers, len, movement, topDistance, translate3d;
    topDistance = this.pageYOffset;
    layers = document.querySelectorAll("[data-type='parallax']");
    for (i = 0, len = layers.length; i < len; i++) {
      layer = layers[i];
      depth = layer.getAttribute('data-depth');
      movement = -(topDistance * depth);
      translate3d = 'translate3d(0, ' + movement + 'px, 0)';
      layer.style['-webkit-transform'] = translate3d;
      layer.style['-moz-transform'] = translate3d;
      layer.style['-ms-transform'] = translate3d;
      layer.style['-o-transform'] = translate3d;
      layer.style.transform = translate3d;
    }
  });
}).call(this);
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiIiwic291cmNlUm9vdCI6IiIsInNvdXJjZXMiOlsiPGFub255bW91cz4iXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7RUFBQTtFQUVBLE1BQU0sQ0FBQyxnQkFBUCxDQUF3QixRQUF4QixFQUFrQyxRQUFBLENBQUMsS0FBRCxDQUFBO0FBQ2hDLFFBQUEsS0FBQSxFQUFBLENBQUEsRUFBQSxLQUFBLEVBQUEsTUFBQSxFQUFBLEdBQUEsRUFBQSxRQUFBLEVBQUEsV0FBQSxFQUFBO0lBQUEsV0FBQSxHQUFjLElBQUMsQ0FBQTtJQUNmLE1BQUEsR0FBUyxRQUFRLENBQUMsZ0JBQVQsQ0FBMEIsd0JBQTFCO0lBRVQsS0FBQSx3Q0FBQTs7TUFDRSxLQUFBLEdBQVEsS0FBSyxDQUFDLFlBQU4sQ0FBbUIsWUFBbkI7TUFDUixRQUFBLEdBQVcsQ0FBQyxDQUFDLFdBQUEsR0FBYyxLQUFmO01BQ1osV0FBQSxHQUFjLGlCQUFBLEdBQW9CLFFBQXBCLEdBQStCO01BQzdDLEtBQUssQ0FBQyxLQUFNLENBQUEsbUJBQUEsQ0FBWixHQUFtQztNQUNuQyxLQUFLLENBQUMsS0FBTSxDQUFBLGdCQUFBLENBQVosR0FBZ0M7TUFDaEMsS0FBSyxDQUFDLEtBQU0sQ0FBQSxlQUFBLENBQVosR0FBK0I7TUFDL0IsS0FBSyxDQUFDLEtBQU0sQ0FBQSxjQUFBLENBQVosR0FBOEI7TUFDOUIsS0FBSyxDQUFDLEtBQUssQ0FBQyxTQUFaLEdBQXdCO0lBUjFCO0VBSmdDLENBQWxDO0FBRkEiLCJzb3VyY2VzQ29udGVudCI6WyIjIFR1dG9yaWFsOiBodHRwczovL21lZGl1bS5jb20vQFBhdHJ5a1phYmllbHNraS9ob3ctdG8tbWFrZS1tdWx0aS1sYXllcmVkLXBhcmFsbGF4LWlsbHVzdHJhdGlvbi13aXRoLWNzcy1qYXZhc2NyaXB0LTJiNTY4ODNjM2YyN1xuXG53aW5kb3cuYWRkRXZlbnRMaXN0ZW5lciAnc2Nyb2xsJywgKGV2ZW50KSAtPlxuICB0b3BEaXN0YW5jZSA9IEBwYWdlWU9mZnNldCBcbiAgbGF5ZXJzID0gZG9jdW1lbnQucXVlcnlTZWxlY3RvckFsbChcIltkYXRhLXR5cGU9J3BhcmFsbGF4J11cIilcbiAgXG4gIGZvciBsYXllciBpbiBsYXllcnNcbiAgICBkZXB0aCA9IGxheWVyLmdldEF0dHJpYnV0ZSgnZGF0YS1kZXB0aCcpXG4gICAgbW92ZW1lbnQgPSAtKHRvcERpc3RhbmNlICogZGVwdGgpXG4gICAgdHJhbnNsYXRlM2QgPSAndHJhbnNsYXRlM2QoMCwgJyArIG1vdmVtZW50ICsgJ3B4LCAwKSdcbiAgICBsYXllci5zdHlsZVsnLXdlYmtpdC10cmFuc2Zvcm0nXSA9IHRyYW5zbGF0ZTNkXG4gICAgbGF5ZXIuc3R5bGVbJy1tb3otdHJhbnNmb3JtJ10gPSB0cmFuc2xhdGUzZFxuICAgIGxheWVyLnN0eWxlWyctbXMtdHJhbnNmb3JtJ10gPSB0cmFuc2xhdGUzZFxuICAgIGxheWVyLnN0eWxlWyctby10cmFuc2Zvcm0nXSA9IHRyYW5zbGF0ZTNkXG4gICAgbGF5ZXIuc3R5bGUudHJhbnNmb3JtID0gdHJhbnNsYXRlM2RcbiAgcmV0dXJuIl19
//# sourceURL=coffeescript
