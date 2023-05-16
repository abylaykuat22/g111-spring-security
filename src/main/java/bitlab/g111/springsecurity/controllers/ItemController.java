package bitlab.g111.springsecurity.controllers;

import bitlab.g111.springsecurity.models.Item;
import bitlab.g111.springsecurity.repositories.ItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController {

  @Autowired
  private ItemRepository itemRepository;

  @GetMapping
  public List<Item> getItems() {
    return itemRepository.findAll();
  }

  @GetMapping("{id}")
  public Item getItem(@PathVariable Long id) {
    return itemRepository.findById(id).orElseThrow();
  }

  @PostMapping
  public Item addItem(@RequestBody Item item) {
    return itemRepository.save(item);
  }

  @PutMapping
  public Item updateItem(@RequestBody Item item) {
    return itemRepository.save(item);
  }

  @DeleteMapping("{id}")
  public void deleteItem(@PathVariable Long id) {
    itemRepository.deleteById(id);
  }
}
