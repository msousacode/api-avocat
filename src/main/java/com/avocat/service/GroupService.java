package com.avocat.service;

import com.avocat.exceptions.ResourceNotFoundException;
import com.avocat.persistence.entity.AuditEntity;
import com.avocat.persistence.entity.Group;
import com.avocat.persistence.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private BranchOfficeService branchOfficeService;

    @Transactional
    public Group create(UUID customerId, Group group) {
        group.setCustomerId(customerId);
        return groupRepository.save(group);
    }

    @Transactional
    public Group update(Group group) {
        var result = getGroup(group.getId());
        return groupRepository.save(Group.from(group, result));
    }

    @Transactional
    public void delete(UUID id) {
        groupRepository.delete(getGroup(id));
    }

    public Page<Group> findAllByCustomerId(UUID customerId, Pageable pageable) {
        return groupRepository.findAllByCustomerId(customerId, pageable);
    }

    public Group findById(UUID id) {
        return getGroup(id);
    }

    private Group getGroup(UUID id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("resource not found"));
    }
}
