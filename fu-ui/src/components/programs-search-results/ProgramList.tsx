import type { Program } from "../../types/program.types";
import ProgramCard from "./ProgramCard";

interface ProgramListProps {
  programs: Program[];
}

const ProgramList = ({ programs }: ProgramListProps) => {
  return (
    <div className="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-2 gap-6">
      {programs.map((program) => (
        <ProgramCard key={program.id} program={program} />
      ))}
    </div>
  );
};
export default ProgramList;
