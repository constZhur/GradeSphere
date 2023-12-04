package ru.mirea.gradesphere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.gradesphere.model.Group;
import ru.mirea.gradesphere.repository.GroupRepository;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getAllGroups(){
        return groupRepository.findAll();
    }

    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group updateGroup(Long id, Group group) {
        Group existingGroup = groupRepository.findById(id).orElse(null);

        if (existingGroup != null) {
            existingGroup.setName(group.getName());
            existingGroup.setDepartment(group.getDepartment());
            existingGroup.setStudents(group.getStudents());

            return groupRepository.save(existingGroup);
        }
        return null;
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}
