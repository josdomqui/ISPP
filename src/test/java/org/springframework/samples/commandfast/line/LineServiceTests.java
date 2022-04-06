package org.springframework.samples.commandfast.line;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.springframework.samples.commandfast.command.Command;
import org.springframework.samples.commandfast.mesa.Mesa;
import org.springframework.samples.commandfast.plate.Plate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class LineServiceTests {

    @Autowired
    private LineService lineService;
    EntityManager em;

    
    @Test
    void shouldFindLine(){
    Mesa m = new Mesa();
    m.setCostumer(5);
    m.setId(1);
    m.setNumber(50);

    Command c = new Command();
    c.setCostumers(5);
    c.setId(1);
    c.setPrice(50.0);
    c.setMesa(m);
    
    Plate p = new Plate();
    p.setCategory("pescado");
    p.setCost(12.50);
    p.setId(1);
    p.setImage("image");

    Line l = new Line();
    l.setCommand(c);
    l.setId(1);
    l.setPlate(p);
    l.setQuantity(30);
    this.lineService.saveline(l);
    Collection<Line> lin = this.lineService.findlines();
    assertThat(lin.isEmpty()).isFalse();
    }

    @Test
    void shouldFindLineById(){
   
    Mesa m = new Mesa();
    m.setCostumer(5);
    m.setId(1);
    m.setNumber(50);

    Command c = new Command();
    c.setCostumers(5);
    c.setId(1);
    c.setPrice(50.0);
    c.setMesa(m);
    
    Plate p = new Plate();
    p.setCategory("pescado");
    p.setCost(12.50);
    p.setId(1);
    p.setImage("image");

    Line l = new Line();
    l.setCommand(c);
    l.setId(1);
    l.setPlate(p);
    l.setQuantity(30);
    this.lineService.saveline(l);

    Line line = this.lineService.findLineById(l.getId()).get();
    
    assertThat(line.getId()).isEqualTo(1);
    }

    @Test
    void shouldFindLineByComandId(){
   
    Mesa m = new Mesa();
    m.setCostumer(5);
    m.setId(1);
    m.setNumber(50);

    Command c = new Command();
    c.setCostumers(5);
    c.setId(1);
    c.setPrice(50.0);
    c.setMesa(m);
    
    Plate p = new Plate();
    p.setCategory("pescado");
    p.setCost(12.50);
    p.setId(1);
    p.setImage("image");

    Line l = new Line();
    l.setCommand(c);
    l.setId(1);
    l.setPlate(p);
    l.setQuantity(30);

    this.lineService.saveline(l);
    List<Line> line = this.lineService.findLineByCommandId(c.getId()).stream().collect(Collectors.toList());
    assertThat(line.get(0).getId()).isEqualTo(1);
    }

    @Test
    void shouldFindLineByComandLineId(){
   
    Mesa m = new Mesa();
    m.setCostumer(5);
    m.setId(1);
    m.setNumber(50);

    Command c = new Command();
    c.setCostumers(5);
    c.setId(1);
    c.setPrice(50.0);
    c.setMesa(m);
    
    Plate p = new Plate();
    p.setCategory("pescado");
    p.setCost(12.50);
    p.setId(1);
    p.setImage("image");

    Line l = new Line();
    l.setCommand(c);
    l.setId(1);
    l.setPlate(p);
    l.setQuantity(30);

    this.lineService.saveline(l);
    Line line = this.lineService.findLineCoById(l.getId(), c.getId()).get();
    assertThat(line.getId()).isEqualTo(1);
    }

    @Test
    void shouldSaveLine(){
    int found = this.lineService.findlines().size();
   
    Mesa m = new Mesa();
    m.setCostumer(5);
    m.setId(1);
    m.setNumber(50);

    Command c = new Command();
    c.setCostumers(5);
    c.setId(1);
    c.setPrice(50.0);
    c.setMesa(m);
    
    Plate p = new Plate();
    p.setCategory("pescado");
    p.setCost(12.50);
    p.setId(1);
    p.setImage("image");

    Line l = new Line();
    l.setCommand(c);
    l.setId(1);
    l.setPlate(p);
    l.setQuantity(30);

    this.lineService.saveline(l);
    List<Line> lineas = this.lineService.findlines().stream().collect(Collectors.toList());


    assertThat(lineas.size()).isEqualTo((found + 1));
    }
    


    

}