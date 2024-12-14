package ca.djmorton.aoc2024.day9.part1

import java.util.*
import kotlin.collections.ArrayList

class DiskMap(private val mapString : String) {
    private var EMPTY_BLOCK = -1
    private var blockList = ArrayList<Int>();
    private var emptySpaceSet = PriorityQueue<Int>()

    private val emptySpaceBlocks = TreeSet<DiskRecord>()


    init {
        initialize(mapString)
    }

    fun defragment(fileLevel : Boolean = false) {
        if (fileLevel) {
            fileLevelDefrag()
        } else {
            blockLevelDefrag()
        }
    }

    private fun blockLevelDefrag() {
        blockList.indices.reversed().forEach { i ->
            if (emptySpaceSet.isNotEmpty()) {
                val fileAtBlock = blockList[i]
                if (fileAtBlock != EMPTY_BLOCK) {
                    val destinationBlock = emptySpaceSet.peek()
                    if (destinationBlock < i) {
                        emptySpaceSet.remove()
                        blockList[i] = EMPTY_BLOCK
                        blockList[destinationBlock] = fileAtBlock
                        emptySpaceSet.add(i)
                    }
                }
            }
        }
    }

    private fun fileLevelDefrag() {
        var index = blockList.size - 1

        while(index >= 0) {
            var fileRecord = scanForFile(index)

            if (fileRecord.fileId != EMPTY_BLOCK) {
                var destinationBlock =
                    emptySpaceBlocks.find { record -> record.length >= fileRecord.length && record.startingIndex < fileRecord.startingIndex }
                if (destinationBlock != null) {
                    moveFile(fileRecord, destinationBlock)
                }
            }

            index = fileRecord.startingIndex - 1
        }
    }

    private fun moveFile(fileRecord : DiskRecord, emptySpace : DiskRecord) {
        writeFile(fileRecord, emptySpace.startingIndex)
        writeFile(DiskRecord(fileRecord.startingIndex, fileRecord.length, EMPTY_BLOCK), fileRecord.startingIndex)
        emptySpaceBlocks.remove(emptySpace)
        if (fileRecord.length != emptySpace.length) {
            emptySpaceBlocks.add(DiskRecord(emptySpace.startingIndex + fileRecord.length, emptySpace.length - fileRecord.length, EMPTY_BLOCK))
        }
        emptySpaceBlocks.add(DiskRecord(fileRecord.startingIndex, fileRecord.length, EMPTY_BLOCK))
    }

    private fun writeFile(fileRecord : DiskRecord, index : Int) {
        (index..(index + fileRecord.length - 1)).forEach { i ->
            blockList.set(i, fileRecord.fileId)
        }
    }

    private fun scanForFile(index : Int) : DiskRecord {
        var startingIndex = index
        var fileIdAtIndex = blockList[startingIndex]

        var candidateStartingIndex = startingIndex - 1

        while(true) {
            if (candidateStartingIndex < 0 || blockList[candidateStartingIndex] != fileIdAtIndex) {
                return DiskRecord(startingIndex, index - startingIndex + 1, fileIdAtIndex)
            } else {
                startingIndex = candidateStartingIndex
                candidateStartingIndex = startingIndex - 1
            }
        }
    }

    fun calculateChecksum() : String {
        return blockList.mapIndexed { index, element ->
            if (element == EMPTY_BLOCK) {
                0L
            } else {
                element * index
            }
        }.map { e -> e.toLong() }.sum().toString()
    }

    private fun initialize(mapString : String) {
        var diskPointer = 0
        blockList.addAll(
            mapString.flatMapIndexed { index, c ->
                val outputArray = ArrayList<Int>()
                val entrySize = c.digitToInt()
                val fileId = if (isPositionFile(index)) fileIdFromPosition(index) else EMPTY_BLOCK

                if (fileId == EMPTY_BLOCK) {
                    emptySpaceBlocks.add(DiskRecord(diskPointer, c.digitToInt(), EMPTY_BLOCK))
                }

                (0..entrySize - 1).forEach {
                    if (fileId == EMPTY_BLOCK) {
                        emptySpaceSet.add(diskPointer)
                    }
                    outputArray.add(fileId)
                    diskPointer++
                }

                outputArray
            }
        )
    }

    override fun toString() : String {
        return blockList.map { block ->
            if (block == EMPTY_BLOCK) {
                "."
            } else {
                block.toString()
            }
        }.joinToString("")
    }

    private fun fileIdFromPosition(index : Int) : Int {
        return index / 2
    }

    private fun isPositionFile(index : Int) : Boolean {
        return index % 2 == 0
    }
}