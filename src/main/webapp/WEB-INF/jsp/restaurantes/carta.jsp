<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<petclinic:layout pageName="carta">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h2 class="display-2 text-center mt-3 mb-0" editable="inline"> Menu</h2>
          <h2 class="text-muted h1 mb-5" editable="inline">Starters</h2>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6">
          <div class="lc-block">
            <div class="item-menu">
              <div class="item-menu-content">
                <p class="item-menu-title"><span editable="inline" class="">Tagliere&nbsp;<i>(Voor twee personen)</i></span> <span editable="inline" class="item-menu-price">21€</span></p>
                <p class="item-menu-desc" editable="inline">Grote proeverij van Italiaanse vleeswaren en kazen, geserveerd met brood<br></p>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="lc-block">
            <div class="item-menu">
              <div class="item-menu-content">
                <p class="item-menu-title"><span editable="inline" class="">Antipasto di Pesce&nbsp;</span> <span editable="inline" class="item-menu-price">12€</span></p>
                <p class="item-menu-desc" editable="inline">Een sauté van mosselen, geserveerd met een bisque van tomaten en kreeft</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6">
          <div class="lc-block">
            <div class="item-menu">
              <div class="item-menu-content">
                <p class="item-menu-title"><span editable="inline">Bruschetta&nbsp;<i>(Vegetarisch)</i></span> <span editable="inline" class="item-menu-price">11€</span></p>
                <p class="item-menu-desc" editable="inline">Tomaten, basilicum, knoflook en olijfolie geserveerd op Altamura brood</p>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="lc-block">
            <div class="item-menu">
              <div class="item-menu-content">
                <p class="item-menu-title"><span editable="inline">Bruschetta Piccante</span> <span editable="inline" class="item-menu-price">12€</span></p>
                <p class="item-menu-desc" editable="inline">Italiaanse pittige salami, gesmolten mozzarella geserveerd op Altamura brood</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6">
          <div class="lc-block">
            <div class="item-menu">
              <div class="item-menu-content">
                <p class="item-menu-title"><span editable="inline">Bruschetta Salmone</span> <span editable="inline" class="item-menu-price">12€</span></p>
                <p class="item-menu-desc" editable="inline">Gerookte zalm, citroen en olijfolie geserveerd op Altamura brood</p>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="lc-block">
            <div class="item-menu">
              <div class="item-menu-content">
                <p class="item-menu-title"><span editable="inline">Insalata Caprese&nbsp;<i>(Vegetarisch)</i></span> <span editable="inline" class="item-menu-price">9€</span></p>
                <p class="item-menu-desc" editable="inline">Tomaten, mozzarella, basilicum</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6">
          <div class="lc-block">
            <div class="item-menu">
              <div class="item-menu-content">
                <p class="item-menu-title"><span editable="inline" class="">Focaccia e Burrata&nbsp;<i>(Vegetarisch)</i></span> <span editable="inline" class="item-menu-price">11€</span></p>
                <p class="item-menu-desc" editable="inline">Onze huisgemaakte focaccia met zongedroogte tomaten en Burrata kaas</p>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="lc-block">
            <div class="item-menu">
              <div class="item-menu-content">
                <p class="item-menu-title"><span editable="inline" class="">Focaccia ai Tre Sapori&nbsp;<i>(Vegetarisch)</i></span> <span editable="inline" class="item-menu-price">8€</span></p>
                <p class="item-menu-desc" editable="inline">3 plakken van onze huisgemaakte focaccia met basilicum, zongedroogde tomaten en ui, geserveerd met olijf olie</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6">
          <div class="lc-block">
            <div class="item-menu">
              <div class="item-menu-content">
                <p class="item-menu-title"><span editable="inline" class="">Fagioli con le Cotiche</span> <span editable="inline" class="item-menu-price">7€</span></p>
                <p class="item-menu-desc" editable="inline">Italiaanse stijl recepte met varken en Borlotti rode bonen&nbsp;</p>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="lc-block">
            <div class="item-menu">
              <div class="item-menu-content">
                <p class="item-menu-title"><span editable="inline" class="">Spinaci Ripassati (Vegetarisch)</span> <span editable="inline" class="item-menu-price">7€</span></p>
                <p class="item-menu-desc" editable="inline">Gebakken spinazie met knoflook en rode chili peper</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6">
          <div class="lc-block">
            <div class="item-menu">
              <div class="item-menu-content">
                <p class="item-menu-title"><span editable="inline" class="">Peperonata (Vegetarisch)</span> <span editable="inline" class="item-menu-price">7€</span></p>
                <p class="item-menu-desc" editable="inline">Gestoofde paprika met tomaten, knoflook, rozemarijn en laurel</p>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="lc-block">
            <div class="item-menu">
              <div class="item-menu-content">
                <p class="item-menu-title"><span editable="inline" class="">Baccalà con i Ceci</span> <span editable="inline" class="item-menu-price">9€</span></p>
                <p class="item-menu-desc" editable="inline">Gebakken kabeljaw en kikkerwten met peper, knoflook, peterselie en citroensap</p>
              </div>
            </div>
          </div>
        </div>
      </div>
          </div>
        </div>
      </div>
    </div>
    
</petclinic:layout>
