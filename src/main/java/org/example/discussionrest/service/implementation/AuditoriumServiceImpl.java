package org.example.discussionrest.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.discussionrest.dto.AuditoriumCreateDto;
import org.example.discussionrest.dto.AuditoriumReadDto;
import org.example.discussionrest.dto.AuditoriumUpdateDto;
import org.example.discussionrest.entity.Auditorium;
import org.example.discussionrest.exception.AuditoriumNotFoundException;
import org.example.discussionrest.mapper.AuditoriumMapper;
import org.example.discussionrest.service.AuditoriumService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuditoriumServiceImpl extends BaseService implements AuditoriumService {

    private final AuditoriumMapper auditoriumMapper;

    @Override
    @Transactional
    public AuditoriumReadDto insertIfNotExists(AuditoriumCreateDto auditoriumCreateDto) {
        Optional<Auditorium> auditorium = auditoriumDao.findByNumber(auditoriumCreateDto.getNumber());
        return auditorium.map(auditoriumMapper::toReadDto).orElseGet(() -> insert(auditoriumCreateDto));
    }

    private AuditoriumReadDto insert(AuditoriumCreateDto auditoriumCreateDto) {
        Auditorium auditorium = auditoriumMapper.toEntity(auditoriumCreateDto);
        auditoriumDao.insert(auditorium);
        return auditoriumMapper.toReadDto(auditorium);
    }

    @Override
    public List<AuditoriumReadDto> findAll() {
        return auditoriumMapper.toReadDto(auditoriumDao.findAll());
    }

    @Override
    public AuditoriumReadDto findOne(int id) throws AuditoriumNotFoundException {
        Auditorium auditorium = findAuditoriumByIdOrElseThrowException(id);
        return auditoriumMapper.toReadDto(auditorium);
    }

    @Override
    @Transactional
    public void update(int id, AuditoriumUpdateDto auditoriumUpdateDto) throws AuditoriumNotFoundException {
        Auditorium auditorium = findAuditoriumByIdOrElseThrowException(id);
        auditoriumMapper.update(auditorium, auditoriumUpdateDto);
    }

    @Override
    @Transactional
    public void delete(int id) throws AuditoriumNotFoundException {
        if (!auditoriumDao.delete(id)) {
            throw exceptionUtil.createAuditoriumNotFoundException(id);
        }
    }
}
