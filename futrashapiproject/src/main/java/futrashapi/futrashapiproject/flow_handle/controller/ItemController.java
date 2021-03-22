package futrashapi.futrashapiproject.flow_handle.controller;


import java.util.List;
import java.util.stream.Collectors;

import futrashapi.futrashapiproject.auth.model.User;
import futrashapi.futrashapiproject.flow_handle.exception.message.ResponseItem;
import futrashapi.futrashapiproject.flow_handle.exception.message.ResponseMessage;
import futrashapi.futrashapiproject.flow_handle.model.Item;
import futrashapi.futrashapiproject.flow_handle.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;


@Controller
//@CrossOrigin("http://localhost:8081")
@RequestMapping("/api/futrash/items")

public class ItemController {


}
