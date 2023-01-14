package com.avocat.controller.group;

import com.avocat.controller.GenericController;
import com.avocat.controller.GenericControllerImpl;
import com.avocat.exceptions.ResourceNotFoundException;
import com.avocat.persistence.entity.Group;
import com.avocat.persistence.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(path = "/v1/groups", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class GroupController extends GenericControllerImpl<Group> {

    /*
    @Autowired
    private GroupRepository groupRepository;

    @PreAuthorize("hasPermission('group', 'write') or hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Group> create(@RequestBody Group group) {
        return ResponseEntity.status(HttpStatus.CREATED).body(groupRepository.save(group));
    }

    @PreAuthorize("hasPermission('group', 'write') or hasRole('ROLE_ADMIN') ")
    @PutMapping
    public ResponseEntity<Group> update(@RequestBody Group group) {
        var result = groupRepository.findById(group.getId()).orElseThrow(() -> new ResourceNotFoundException("resource not found"));
        var updated = Group.from(group, result);
        return ResponseEntity.status(HttpStatus.OK).body(groupRepository.save(group));
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<Group> findById(@PathVariable("groupId") UUID groupId) throws Exception {
        var result = groupRepository.findById(groupId).orElseThrow(() -> new ResourceNotFoundException("resource not found"));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping
    public ResponseEntity<List<Group>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(groupRepository.findAll());
    }*/
}