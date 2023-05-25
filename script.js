(function() { 

    var slides = document.querySelectorAll('.slider li'); 

    var currentSlide = 0; 

    var slideInterval = setInterval(nextSlide, 5000); // Define o intervalo de tempo para trocar os slides (3000ms = 3 segundos) 

 

    function nextSlide() { 

        slides[currentSlide].querySelector('input').checked = false; 

        currentSlide = (currentSlide + 1) % slides.length; 

        slides[currentSlide].querySelector('input').checked = true; 

    } 

})(); 

 

 

 // Função para realizar a pesquisa 

 function search() { 

    var input = document.querySelector('.search-input'); 

    var searchText = input.value.toLowerCase(); 

    var results = document.querySelector('.search-results'); 

 

    // Limpa os resultados anteriores 

    results.innerHTML = ''; 

 

    // Verifica cada item da lista 

    var items = document.querySelectorAll('.menu-item'); 

    items.forEach(function(item) { 

      var itemName = item.textContent.toLowerCase(); 

 

      // Se o nome do item corresponder à pesquisa, adiciona-o aos resultados 

      if (itemName.indexOf(searchText) !== -1) { 

        var li = document.createElement('li'); 

        var link = document.createElement('a'); 

        var sectionId = item.closest('.section').id; 

        link.href = '#' + sectionId; 

        link.textContent = item.textContent; 

        li.appendChild(link); 

        results.appendChild(li); 

      } 

    }); 

 

    // Se não houver resultados, exibe uma mensagem 

    if (results.childElementCount === 0) { 

      var li = document.createElement('li'); 

      li.textContent = 'Nenhum resultado encontrado.'; 

      results.appendChild(li); 

    } 

 

    // Rolar para a seção correspondente após a pesquisa 

    var section = document.querySelector('#' + searchText); 

    if (section) { 

      section.scrollIntoView({ behavior: 'smooth' }); 

    } 

  } 

 

  // Chama a função de pesquisa quando o botão é clicado 

  var button = document.querySelector('.search-button'); 

  button.addEventListener('click', search); 

 

  // Chama a função de pesquisa ao pressionar a tecla "Enter" no campo de pesquisa 

  var input = document.querySelector('.search-input'); 

  input.addEventListener('keyup', function(event) { 

    if (event.key === 'Enter') { 

      search(); 

    } 

  }); 