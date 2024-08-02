package org.example.discussionrest.service.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.discussionrest.dto.DiscussionCreateDto;
import org.example.discussionrest.dto.DiscussionReadDto;
import org.example.discussionrest.dto.DiscussionUpdateDto;
import org.example.discussionrest.entity.Auditorium;
import org.example.discussionrest.entity.Discussion;
import org.example.discussionrest.exception.AuditoriumNotFoundException;
import org.example.discussionrest.exception.DiscussionNotFoundException;
import org.example.discussionrest.exception.UserNotFoundException;
import org.example.discussionrest.mapper.DiscussionMapper;
import org.example.discussionrest.service.DiscussionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DiscussionServiceImpl extends BaseService implements DiscussionService {

    private final DiscussionMapper discussionMapper;

    @Override
    @Transactional
    public DiscussionReadDto insert(DiscussionCreateDto discussionCreateDto) throws AuditoriumNotFoundException {
        Auditorium auditorium = findAuditoriumByIdOrElseThrowException(discussionCreateDto.getAuditoriumId());
        Discussion discussion = discussionMapper.toEntity(discussionCreateDto);
        discussion.setAuditorium(auditorium);

        discussionDao.insert(discussion);
        return discussionMapper.toReadDto(discussion);
    }

    @Override
    public List<DiscussionReadDto> findAll() {
        return discussionMapper.toReadDto(discussionDao.findAllWithAuditorium());
    }

    @Override
    public List<DiscussionReadDto> findAllByUserId(int userId) throws UserNotFoundException {
        return discussionMapper.toReadDto(findUserByIdOrElseThrowException(userId, FetchType.WITH_DISCUSSIONS_AND_AUDITORIUM).getDiscussions());
    }

    @Override
    public DiscussionReadDto findOne(int id) throws DiscussionNotFoundException {
        Discussion discussion = findDiscussionByIdOrElseThrowException(id, FetchType.WITH_AUDITORIUM);
        return discussionMapper.toReadDto(discussion);
    }

    @Override
    @Transactional
    public void update(int id, DiscussionUpdateDto discussionUpdateDto) throws DiscussionNotFoundException, AuditoriumNotFoundException {
        Discussion discussion = findDiscussionByIdOrElseThrowException(id, FetchType.WITH_AUDITORIUM);
        if (discussion.getAuditorium().getId() != discussionUpdateDto.getAuditoriumId()) {
            Auditorium auditorium = findAuditoriumByIdOrElseThrowException(discussionUpdateDto.getAuditoriumId());
            discussion.setAuditorium(auditorium);
        }

        discussionMapper.update(discussion, discussionUpdateDto);
    }

    @Override
    @Transactional
    public void delete(int id) throws DiscussionNotFoundException {
        if (!discussionDao.delete(id)) {
            throw exceptionUtil.createDiscussionNotFoundException(id);
        }
    }
}
